/**
 * Copyright (c) 2018, INTech.
 * this file is part of INTech's HighLevel.

 * INTech's HighLevel is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * INTech's HighLevel is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with it.  If not, see <http://www.gnu.org/licenses/>.
 **/

package data.controlers;

/**
 * Définit les canaux de communication & header
 *
 * @author rem
 */
public enum Channel {
    //DataControler

    //Du LL
    LL_DEBUG('@', 'C'),
    ROBOT_POSITION('@', 'P'),
    COULEUR_GOBELET_PRIS('!', '&'),

    SICK('@', 'A'),

    //De buddy
    BUDDY_POSITION('!', '#'),
    BUDDY_PATH('!', 'C'),
    UPDATE_GOBELETS('!', 'H'),

    BUDDY_EVENT('!', 'E'),

    //De l'IA
    SCRIPTS('!', 'O'),

    //Pour l'IA
    EVENTS('@', 'B'),
    GOBELETS_ASCENSEUR('@', 'D'),

    //LidarControler
    OBSTACLES('!', '!'),
    RAW_LIDAR_DATA('!', 'R'),

    // CylinderDetection
    CYLINDERS('!', '§'),

    //Gobelet Position
    GOBELETS_POSITION('!', '-'),

    // Config gobelets
    CONFIG_ECEUIL('y', ';')
    ;

    /**
     * Headers
     */
    private String headers;

    /**
     * Consruit un canal de com
     * @param h1    char 1
     * @param h2    char 2
     */
    Channel(char h1, char h2) {
        char[] h = {h1, h2};
        this.headers = new String(h);
    }

    /**
     * Getter
     */
    public String getHeaders() {
        return headers;
    }


    @Override
    public String toString() {
        return headers;
    }
}
