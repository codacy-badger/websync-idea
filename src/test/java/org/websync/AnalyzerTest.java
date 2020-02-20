package org.websync;

import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiClassOwner;
import com.intellij.psi.PsiFile;
import com.intellij.psi.search.FilenameIndex;
import com.intellij.psi.search.GlobalSearchScope;
import com.intellij.psi.search.PsiShortNamesCache;
import com.intellij.testFramework.fixtures.LightJavaCodeInsightFixtureTestCase;
import org.junit.Test;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Instant;
import java.util.Arrays;

public class AnalyzerTest extends LightJavaCodeInsightFixtureTestCase {
//public class AnalyzerTest extends HeavyPlatformTestCase {

    String rootPath = "C:\\Users\\Vitalii_Balitckii\\IdeaProjects\\jdi-light-testng-template\\";
    String srcPath = "src\\";
    String javaPath = "main\\java\\";
    String classPath = "org\\mytests\\uiobjects\\example\\site\\pages\\";
    String exampleClassFile = "DatesPage.java";
    Path path = Paths.get(rootPath + srcPath);

    @Override
    public String getTestDataPath() {
        return rootPath + srcPath;
    }

    @Override
    public String getBasePath() {
        return rootPath + srcPath;
    }

    //    @Override
    protected Path getProjectDirOrFile() {
        return path;
    }

    @Test
    public void test0() {
        long startTime = System.nanoTime();
        myFixture.copyDirectoryToProject("", "");
        long endTime = System.nanoTime();
        System.out.println(endTime - startTime);

        System.out.println();
        System.out.println("Filenames:");
        Arrays.stream(FilenameIndex.getAllFilenames(getProject())).forEach(f -> {
            System.out.println("- " + f);
        });

        GlobalSearchScope projectScope = GlobalSearchScope.allScope(getProject());

        System.out.println();
        System.out.println("PsiFiles:");
        Arrays.stream(FilenameIndex.getAllFilenames(getProject())).forEach(f -> {
            Arrays.stream(FilenameIndex.getFilesByName(getProject(), f, projectScope)).forEach(psiFile -> {
                System.out.println("- " + psiFile.getVirtualFile().getPath());
            });
        });

        System.out.println();
        System.out.println("Classes:");
        Arrays.stream(PsiShortNamesCache.getInstance(getProject()).getAllClassNames()).forEach(c -> {
            System.out.println("- " + c);
        });

        System.out.println();
        System.out.println("PsiClasses:");
        Arrays.stream(FilenameIndex.getAllFilenames(getProject()))
                .filter(f -> f.endsWith(".java"))
                .forEach(f -> {
                    Arrays.stream(FilenameIndex.getFilesByName(getProject(), f, projectScope))
                            .forEach(psiFile -> {
                                PsiClass psiClass = ((PsiClassOwner) psiFile).getClasses()[0];
                                System.out.println("- " + psiClass.getQualifiedName());
                            });
                });

        // Start Analyzer of JDI Project structure
        // ...

        // Some tests
        // ...
        System.out.println();
    }

    @Test
    public void test1() {
        long startTime = System.nanoTime();
        PsiFile[] psiFiles = myFixture.configureByFiles(
                javaPath + classPath + "ContactFormPage.java",
                javaPath + classPath + "ContactsPage.java",
                javaPath + classPath + "DatesPage.java",
                javaPath + classPath + "HomePage.java",
                javaPath + classPath + "Html5Page.java",
                javaPath + classPath + "JDIPerformancePage.java",
                javaPath + classPath + "UsersPage.java"
        );
        long endTime = System.nanoTime();
        System.out.println(endTime - startTime);

        // example
        PsiClass clazz = myFixture.findClass("org.mytests.uiobjects.example.site.pages.ContactFormPage");

        // example
        PsiShortNamesCache.getInstance(getProject()).getAllClassNames();

        // Some tests
        // ...
    }

    public void test2() {
        long startTime = System.nanoTime();
        VirtualFile virtualFile = myFixture.copyFileToProject(javaPath + classPath + "ContactFormPage.java");
        PsiFile psiFile = myFixture.getPsiManager().findFile(virtualFile);

        long endTime = System.nanoTime();
        System.out.println(endTime - startTime);

        // example
        PsiClass clazz = myFixture.findClass("org.mytests.uiobjects.example.site.pages.ContactFormPage");

        // example
        PsiShortNamesCache.getInstance(getProject()).getAllClassNames();

        // Some tests
        // ...
    }
}
