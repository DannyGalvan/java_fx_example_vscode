package com.mycompany.loan_control;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.mycompany.loan_control.dbContext.PersistManager;
import com.mycompany.loan_control.utils.ClassUtils;
import com.mycompany.loan_control.utils.Params;
import com.mycompany.loan_control.utils.StringUtils;

import io.github.cdimascio.dotenv.Dotenv;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;
    private static Stage primaryStage;
    private static String nameConfiguration = ".env";
    private static Dotenv config = Dotenv.configure().filename(nameConfiguration).load();
    private static final Map<String, Class<?>> controllerMap = new HashMap<>();

    @Override
    public void init()  {
        try {
            super.init();
            // Escanea el paquete que contiene los controladores y agrega las clases de
            // controladores al mapa de controladores.
            Set<Class<?>> controllerClasses =  ClassUtils.getClasses(config.get("CONTROLLERS_PACKAGE"));;
            
            for (Class<?> controllerClass : controllerClasses) {
                if (controllerClass.getSimpleName().endsWith("Controller")) {
                    String packageName = controllerClass.getName();
                    String[] packageStrings = packageName.split("\\.");
                    int length = packageStrings.length;
                    String controller = packageStrings[length - 2].equals(config.get("CONTROLLERS_DIR_PACKAGE")) ? ""
                            : packageStrings[length - 2];
                    String view = packageStrings[length - 1];
                    String hasPoint = controller.equals("") ? "" : "/";
                    String viewName = StringUtils.decapitalize(controller + hasPoint + view.replace("Controller", ""));                   
                    controllerMap.put(viewName, controllerClass);
                }
            }
        } catch (Exception e) {
            System.out.println("Error al escanear los controladores: " + e.getMessage());
            System.out.println(e.getCause());
            System.exit(1);
        }        
    }

    @Override
    public void start(Stage stage) throws IOException {
        try {
            primaryStage = stage;
            scene = new Scene(Location.loadFXML(config.get("CONTROLLER"),config.get("VIEW")),1000, 800);
            scene.getStylesheets().add(Location.loadCSS("Global").toExternalForm());        
            primaryStage.setScene(scene);
            primaryStage.getIcons().add(new Image(App.class.getResource("/images/Emsa.jpg").toString()));
            primaryStage.show();
        } catch (Exception e) {
            System.out.println("Error al iniciar la aplicación: " + e.getMessage());
            System.out.println("Controladores disponibles:");
            controllerMap.forEach((key, value) -> System.out.println(key));
            throw e;
        }
    }

     /**
    * Establece el controlador raíz de la aplicación.
    *
    * @param controller El nombre del controlador raíz.
    */
    public static void setRoot(String controller) {
        scene.setRoot(Location.loadFXML(controller));
    }
    
     /**
    * Establece el controlador raíz de la aplicación.
    *
    * @param controller El nombre del controlador raíz.
    * @param inf recibe parametros para otro controlador
    */
    public static void setRoot(String controller, Params<?> inf) {
        scene.setRoot(Location.loadFXML(controller, inf));
    }

     /**
    * Establece el controlador raíz de la aplicación.
    *
    * @param controller El nombre del controlador raíz.
    * @param action Define una accion a donde queremos irnos
    */
    public static void setRoot(String controller, String action) {
        scene.setRoot(Location.loadFXML(controller,action));
    }    
    
      /**
    * Establece el controlador raíz de la aplicación.
    *
    * @param controller El nombre del controlador raíz.
    * @param action Define una accion a donde queremos irnos
    * @param inf recibe parametros para ir a otro controlador y vista
    */
    public static void setRoot(String controller, String action, Params<?> inf) {
        scene.setRoot(Location.loadFXML(controller,action,inf));
    }
     
      /**
    * cambia de vista la aplicacion
    *
    * @param action El nombre de la vista
    */
    public static void view(String action){
        scene = new Scene(Location.loadView(action),1000, 800);
        scene.getStylesheets().add(Location.loadCSS("Global").toExternalForm());        
        primaryStage.setScene(scene);
        primaryStage.setResizable(true);
        primaryStage.show();
    }
    
      /**
    * cambia de vista la aplicacion
    *
    * @param action El nombre de la vista
    */
    public static void view(String action, double width, double height, Params<?> inf){
        scene = new Scene(Location.loadView(action,inf),width,height);
        scene.getStylesheets().add(Location.loadCSS("Global").toExternalForm());   
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();        
    }
    
      /**
    * cambia de vista la aplicacion
    *
    * @param action El nombre de la vista
    * @param inf recibe parametros a pasar a una vista
    */
    public static void view(String action, Params<?> inf){
        scene = new Scene(Location.loadView(action, inf),1000, 800);
        scene.getStylesheets().add(Location.loadCSS("Global").toExternalForm());        
        primaryStage.setScene(scene);
        primaryStage.setResizable(true);
        primaryStage.show();
    }

    public static void main(String[] args) {
        try {
            launch();
        } catch (Exception e) {
           System.out.println("Error al iniciar la aplicación: " + e.getMessage());
           System.out.println(e.getCause());
        }
    }

    public static String getNameConfiguration() {
        return nameConfiguration;
    }

    public static Map<String, Class<?>> getControllerMap() {
        return controllerMap;
    }

    @Override
    public void stop() throws Exception {
        super.stop();

        PersistManager pm = IoCContainer.getInstance(PersistManager.class);
        pm.close();

        System.out.println("La aplicación se ha cerrado, se ha cerrado la conexión a la base de datos.");
    }
}