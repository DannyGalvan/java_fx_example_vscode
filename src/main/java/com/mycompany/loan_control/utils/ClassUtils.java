package com.mycompany.loan_control.utils;

import java.util.HashSet;
import java.util.Set;

import io.github.classgraph.ClassGraph;
import io.github.classgraph.ClassInfo;
import io.github.classgraph.ScanResult;

public class ClassUtils {

  public static Set<Class<?>> getClasses(String packageName) {
    Set<Class<?>> classes = new HashSet<>();
    try (
        ScanResult scanResult = new ClassGraph()
            .enableAllInfo()
            .acceptPackages(packageName)
            .scan()) {

      for (ClassInfo classInfo : scanResult.getAllClasses()) {
        classes.add(Class.forName(classInfo.getName()));
      }
    } catch (ClassNotFoundException e) {
      System.out.println("Error al escanear los controladores: " + e.getMessage());
      System.out.println(e.getCause());
      System.out.println(e.getStackTrace());
    }
    return classes;
  }
}
