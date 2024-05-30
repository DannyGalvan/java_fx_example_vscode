package com.mycompany.loan_control.utils;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class ClassUtils {

  private static Set<Class<?>> findClasses(File directory, String packageName) throws ClassNotFoundException {
    Set<Class<?>> classes = new HashSet<>();
    if (!directory.exists()) {
      return classes;
    }
    File[] files = directory.listFiles();
    for (File file : files) {
      if (file.isDirectory()) {
        assert !file.getName().contains(".");
        classes.addAll(findClasses(file, packageName + "." + file.getName()));
      } else if (file.getName().endsWith(".class")) {
        classes.add(Class.forName(packageName + '.' + file.getName().substring(0, file.getName().length() - 6)));
      }
    }
    return classes;
  }

  public static Set<Class<?>> getClasses(String packageName) throws ClassNotFoundException, IOException {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        String path = packageName.replace('.', '/');
        Enumeration<URL> resources = classLoader.getResources(path);
        Set<Class<?>> classes = new HashSet<>();
        while (resources.hasMoreElements()) {
            URL resource = resources.nextElement();
            if (resource.getProtocol().equals("jar")) {
                classes.addAll(findClassesFromJar(resource, packageName));
            } else {
                classes.addAll(findClasses(new File(resource.getFile()), packageName));
            }
        }
        return classes;
    }

    private static Set<Class<?>> findClassesFromJar(URL jarUrl, String packageName) throws IOException, ClassNotFoundException {
        Set<Class<?>> classes = new HashSet<>();
        String jarPath = jarUrl.getPath();
        try (JarFile jarFile = new JarFile(jarPath.substring(jarPath.indexOf(':') + 1, jarPath.indexOf('!')))) {
            Enumeration<JarEntry> entries = jarFile.entries();
            URLClassLoader loader = new URLClassLoader(new URL[]{jarUrl});
            while (entries.hasMoreElements()) {
                JarEntry entry = entries.nextElement();
                if (!entry.isDirectory() && entry.getName().endsWith(".class")) {
                    String className = entry.getName().replace('/', '.').replace(".class", "");
                    if (className.startsWith(packageName)) {
                        Class<?> clazz = Class.forName(className, true, loader);
                        classes.add(clazz);
                    }
                }
            }
        }
        return classes;
    }
}
