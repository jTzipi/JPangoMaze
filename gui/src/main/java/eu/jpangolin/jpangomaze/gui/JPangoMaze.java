/*
 * Copyright (c) 2024 Tim Langhammer.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package eu.jpangolin.jpangomaze.gui;

import eu.jpangolin.jpangomaze.gui.base.MainFrame;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * GUI Start point.
 */
public class JPangoMaze extends Application {


    @Override
    public void start(Stage stage) throws Exception {

        stage.setTitle("PangoMaze");
        stage.setScene(new Scene(MainFrame.instance(), 1200D, 1200D));
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}