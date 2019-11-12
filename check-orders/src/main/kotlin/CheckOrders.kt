import java.io.File
import java.lang.reflect.Modifier
import java.net.URL
import java.net.URLClassLoader
import java.util.zip.ZipInputStream
import kotlin.concurrent.thread
import kotlin.system.exitProcess

fun main() {
    print(File(".").canonicalPath)
    // on lance le build
    val gradleProcess = ProcessBuilder("./gradlew", "build", "-x", "test").start()
    thread(isDaemon = true) { // affichage de la sortie de Gradle dans la console
        gradleProcess.inputStream.bufferedReader().useLines { it.forEach(::println) }
    }
    val errorCode = gradleProcess.waitFor()
    println("Finished with error code $errorCode")

    if(errorCode != 0) {
        println("An error occured, can't check orders")
        exitProcess(1)
    }

    // on récupère le jar le plus récent
    val commonBuildFolder = File("T3HL-common/build/libs/")
    val builds = commonBuildFolder.listFiles() ?: error("No build performed? Make sure Gradle can build the project")
    val latestBuild = builds.maxBy { it.lastModified() }!!

    // on ajoute le jar dans le classpath
    val zip = ZipInputStream(latestBuild.inputStream())
    val loader = object: URLClassLoader(emptyArray(), ClassLoader.getSystemClassLoader()) {
        public override fun addURL(url: URL?) {
            super.addURL(url)
        }
    }
    loader.addURL(latestBuild.toURI().toURL())
    println("Adding jar file ${latestBuild.absolutePath} to classpath")

    val orderClass = loader.loadClass("lowlevel.order.Order")
    val toLL = orderClass.getMethod("toLL")
    val orderWithArgClass = loader.loadClass("lowlevel.order.OrderWithArgument")
    val getBase = orderWithArgClass.getMethod("getBase")
    val orders = mutableListOf<String>()
    while(zip.available() > 0) { // pour chaque class présente dans le .jar, on la charge et on vérifie si il y a des champs de type Order qui sont statiques
        val entry = zip.nextEntry ?: break
        if(!entry.name.endsWith(".class") || entry.name.endsWith("module-info.class"))
            continue
        println("Inpecting class file ${entry.name}")

        val loadedClass = loader.loadClass(entry.name.substringBeforeLast(".").replace("/", "."))
        println("\tLoaded class name is ${loadedClass.canonicalName}")

        // chargement des champs de la class
        val fields = loadedClass.declaredFields
        for(field in fields.filter { it.modifiers and Modifier.STATIC != 0 && orderClass.isAssignableFrom(it.type) }) { // on prend les champs statiques de type Order
            try {
                val llString =
                if(orderWithArgClass.isAssignableFrom(field.type)) {
                    getBase(field.get(null)) as String // cas spécial pour OrderWithArgument
                } else {
                    toLL(field.get(null)) as String
                }
                println("\t > Found ${field.type.canonicalName} ${field.name} => $llString")
                orders += llString
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
    zip.close()

    // récupération des ordres distincts
    val distinctOrders = orders.map { it.split(" ").first() }.distinct()
    println()
    println("Writing files")
    File(".order_list").writeText(orders.joinToString("\n"))
    File(".distinct_order_list").writeText(distinctOrders.joinToString("\n"))
}


