package com.mycompany.loan_control;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import com.mycompany.loan_control.utils.Params;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

public class Location {
  private static String controller;
  private static String action;
  private static Params<?> params = new Params<>();

  /**
   * @return the controller
   */
  public static String getController() {
    return controller;
  }

  /**
   * @param controller the controller to set
   */
  private static void setController(String controller) {
    Location.controller = controller;
  }

  /**
   * @return the action
   */
  public static String getAction() {
    return action;
  }

  /**
   * @param action the action to set
   */
  private static void setAction(String action) {
    Location.action = action;
  }

  /**
   * @return the params
   */
  public static Params<?> getParams() {
    return params;
  }

  /**
   * @param aParams the params to set
   */
  public static void setParams(Params<?> aParams) {
    params = aParams;
  }

  /**
   * metodo se utiliza para cargar una ruta especifica de los recursos
   * 
   * @param path
   * @return
   */
  public static URL loadPath(String path) {
    URL ProjectRoot = App.class.getResource(path);
    return ProjectRoot;
  }

  /**
   * metodo para cargar archivos css
   * 
   * @param name recibe el nombre del archivo sin la extencion css
   * @return
   */
  protected static URL loadCSS(String name) {
    URL ProjectRoot = App.class.getResource("/styles/" + name + ".css");
    return ProjectRoot;
  }

  private static Parent setInitialView() {
    URL ProjectRoot;
    try {
      String nameView = "home/index";
      Class<?> View = App.getControllerMap().get(controller + "/index");
      if (View == null) {
        System.out.println("Controladores disponibles:");
        App.getControllerMap().forEach((key, value) -> System.out.println(key));
        throw new IllegalArgumentException("No se encontró el controlador para la vista: " + nameView);
      }
      ProjectRoot = App.class.getResource("/views/"+nameView+".fxml");
      FXMLLoader fxmlLoader = new FXMLLoader(ProjectRoot);
      fxmlLoader.setController(IoCContainer.getInstance(View));
      setAction("index");
      setController("home");
      return fxmlLoader.load();
    } catch (MalformedURLException ex1) {
      System.out.print(ex1.toString());
    } catch (IOException ex1) {
      System.out.print(ex1.toString());
    }
    return null;
  }

  /**
   * metodo para cambiar de controlador a la vista
   * principal del mismo
   * 
   * @param controller nombre del controlador
   * @return a Parent
   */
  protected static Parent loadFXML(String controller) {
    try {
      String nameView = controller + "/index";
      Class<?> View = App.getControllerMap().get(nameView);
      if (View == null) {
        System.out.println("Controladores disponibles:");
        App.getControllerMap().forEach((key, value) -> System.out.println(key));
        throw new IllegalArgumentException("No se encontró el controlador para la vista: " + nameView);
      }
      URL ProjectRoot = App.class.getResource("/views/" + nameView + ".fxml");
      FXMLLoader fxmlLoader = new FXMLLoader(ProjectRoot);
      fxmlLoader.setController(IoCContainer.getInstance(View));
      setAction("index");
      setController(controller);
      return fxmlLoader.load();
    } catch (IOException ex) {
      System.out.println("No Existe el controllador o la vista " + ex.toString());
      return setInitialView();
    }
  }

  /**
   * metodo para cambiar de controlador pasando
   * un parametro a la vista
   * 
   * @param controller nombre del controlador
   * @param info       el parametro del tipo deseado
   * @return a Parent
   */
  protected static Parent loadFXML(String controller, Params<?> info) {
    try {
      String nameView = controller + "/index";
      Class<?> View = App.getControllerMap().get(nameView);
      if (View == null) {
        System.out.println("Controladores disponibles:");
        App.getControllerMap().forEach((key, value) -> System.out.println(key));
        throw new IllegalArgumentException("No se encontró el controlador para la vista: " + nameView);
      }
      URL ProjectRoot = App.class.getResource("/views/" + nameView + ".fxml");
      FXMLLoader fxmlLoader = new FXMLLoader(ProjectRoot);
      fxmlLoader.setController(IoCContainer.getInstance(View));
      setAction("index");
      setController(controller);
      setParams(info);
      return fxmlLoader.load();
    } catch (IOException ex) {
      System.out.println("No Existe el controllador o la vista " + ex.toString());
      return setInitialView();
    }
  }

  /**
   * metodo para cambiar de controlador a una
   * vista especifica
   * 
   * @param controller nombre del controlador
   * @param action     nombre de la vista a la cual quieres ir
   * @return a Parent
   */
  protected static Parent loadFXML(String controller, String action) {
    try {
      String nameView = controller + "/" + action;
      Class<?> View = App.getControllerMap().get(nameView);
      if (View == null) {
        System.out.println("Controladores disponibles:");
        App.getControllerMap().forEach((key, value) -> System.out.println(key));
        throw new IllegalArgumentException("No se encontró el controlador para la vista: " + nameView);
      }
      URL ProjectRoot = App.class.getResource("/views/" + nameView + ".fxml");
      FXMLLoader fxmlLoader = new FXMLLoader(ProjectRoot);
      fxmlLoader.setController(IoCContainer.getInstance(View));
      setAction(action);
      setController(controller);
      return fxmlLoader.load();
    } catch (IOException ex) {
      System.out.println("No Existe el controllador o la vista " + ex.toString());
      System.out.println(ex.getCause());
      return setInitialView();
    }
  }

  /**
   * metodo para cambiar de controlador
   * y vista incluyendo parametros a la vista
   * 
   * @param controller nombre del controlador
   * @param action     nombre de la vista
   * @param info       parametros deceados
   * @return a Parent
   */
  protected static Parent loadFXML(String controller, String action, Params<?> info) {
    try {
      String nameView = controller + "/" + action;
      Class<?> View = App.getControllerMap().get(nameView);
      if (View == null) {
        System.out.println("Controladores disponibles:");
        App.getControllerMap().forEach((key, value) -> System.out.println(key));
        throw new IllegalArgumentException("No se encontró el controlador para la vista: " + nameView);
      }
      URL ProjectRoot = App.class.getResource("/views/" + nameView + ".fxml");
      FXMLLoader fxmlLoader = new FXMLLoader(ProjectRoot);
      fxmlLoader.setController(IoCContainer.getInstance(View));
      setAction(action);
      setController(controller);
      setParams(info);
      return fxmlLoader.load();
    } catch (IOException ex) {
      System.out.println("No Existe el controllador o la vista " + ex.toString());
      return setInitialView();
    }
  }

  /**
   * metodo para cargar una vista sin
   * cambiar el controlador
   * 
   * @param view nombre de la vista, cambia en el mismo controlador
   * @return a Parent
   */
  protected static Parent loadView(String view) {
    try {
      String nameView = Location.controller + "/" + view;
      Class<?> View = App.getControllerMap().get(nameView);
      if (View == null) {
        System.out.println("Controladores disponibles:");
        App.getControllerMap().forEach((key, value) -> System.out.println(key));
        throw new IllegalArgumentException("No se encontró el controlador para la vista: " + nameView);
      }
      URL ProjectRoot = App.class.getResource("/views/" + nameView + ".fxml");
      FXMLLoader fxmlLoader = new FXMLLoader(ProjectRoot);
      fxmlLoader.setController(IoCContainer.getInstance(View));
      setAction(view);
      setController(Location.controller);
      return fxmlLoader.load();
    } catch (IOException ex) {
      System.out.println("No Existe el controllador o la vista " + ex.toString());
      return setInitialView();
    }
  }

  /**
   * metodo para cambiar de vista
   * dentro del mismo controlador incluyendo
   * un parametro
   * 
   * @param view nombre de la vista
   * @param inf  parametros deseados
   * @return a Parent
   */
  protected static Parent loadView(String view, Params<?> inf) {
    try {
      String nameView = Location.controller + "/" + view;
      Class<?> View = App.getControllerMap().get(nameView);
      if (View == null) {
        System.out.println("Controladores disponibles:");
        App.getControllerMap().forEach((key, value) -> System.out.println(key));
        throw new IllegalArgumentException("No se encontró el controlador para la vista: " + nameView);
      }
      URL ProjectRoot = App.class.getResource("/views/" + nameView + ".fxml");
      FXMLLoader fxmlLoader = new FXMLLoader(ProjectRoot);
      fxmlLoader.setController(IoCContainer.getInstance(View));
      setAction(view);
      setController(Location.controller);
      setParams(inf);
      return fxmlLoader.load();
    } catch (IOException ex) {
      System.out.println("No Existe el controllador o la vista " + ex.toString());
      return setInitialView();
    }
  }
}
