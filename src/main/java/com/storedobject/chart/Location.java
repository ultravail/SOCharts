/*
 *  Copyright 2019-2020 Syam Pillai
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *
 */

package com.storedobject.chart;

/**
 * Representation of a location.
 *
 * @author Syam
 */
public enum Location {
    START,
    TOP,
    MIDDLE,
    CENTER,
    BOTTOM,
    END;

    static Location h(Location location) {
        if(location == null) {
            return END;
        }
        switch (location) {
            case START:
            case TOP:
                return START;
            case MIDDLE:
            case CENTER:
                return CENTER;
        }
        return END;
    }
}
