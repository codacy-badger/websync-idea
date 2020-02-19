package org.websync;

import com.intellij.openapi.project.Project;
import com.intellij.psi.JavaPsiFacade;
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiDocumentManager;
import com.intellij.psi.search.GlobalSearchScope;
import com.intellij.psi.search.searches.ClassInheritorsSearch;
import com.intellij.testFramework.HeavyPlatformTestCase;
import com.intellij.testFramework.fixtures.IdeaTestFixtureFactory;
import com.intellij.testFramework.fixtures.LightJavaCodeInsightFixtureTestCase;
import org.junit.Assert;
import org.junit.Test;
import org.websync.jdi.JdiElement;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class AnalyzerTest extends LightJavaCodeInsightFixtureTestCase {
//public class AnalyzerTest extends HeavyPlatformTestCase {

//    @Test
//    public void getProjectStructure() {
//        Assert.assertEquals(0, 0);
//        // Open IDE
//        // ...
//
//        // Open JDI Project
//        // ...
//
//        // Close window 'Tip of the Day'
//        // ...
//
//        // Wait opening project files, indexing project files and downloading project dependencies
//        // ...
//
//        // Get PSI structure of JDI Project
//        // ...
//
//        // Start Analyzer of JDI Project structure
//        // ...
//
//        // Some tests
//        // ...
//
//        // Close IDE
//        // ...
//
//        // Close dialog 'Confirm Exit'
//        // ...
//    }

    String rootPath = "C:\\Users\\Vitalii_Balitckii\\IdeaProjects\\jdi-light-testng-template\\";
    Path path = Paths.get(rootPath);

    @Override
    public String getTestDataPath()
    {
        return rootPath;
    }

    @Override
    public String getBasePath() {
        return rootPath;
    }

//    @Override
    protected Path getProjectDirOrFile() {
        return path;
    }

    @Test
    public void test() {
//        IdeaTestFixtureFactory.getFixtureFactory().createFixtureBuilder();
//        JavaTestFixtureFactoryImpl.
//        HeavyPlatformTestCase.doAutodetectPlatformPrefix();
        // Open java classes in project directory
//        Project project = HeavyPlatformTestCase.createProject(path);

        // Get PSI structure of JDI Project
        PsiDocumentManager.getInstance(getProject()).commitAllDocuments();
        getProject().getProjectFilePath();

//        JavaPsiFacade javaPsiFacade = JavaPsiFacade.getInstance(getProject());
        JavaPsiFacade javaPsiFacade = super.myFixture.getJavaFacade();
//        super.myFixture.copyDirectoryToProject()
        GlobalSearchScope allScope = GlobalSearchScope.allScope(getProject());

        PsiClass clazz = myFixture.findClass("org.mytests.uiobjects.example.site.pages.ContactFormPage");

        PsiClass webPagePsiClass = javaPsiFacade.findClass(JdiElement.JDI_WEB_PAGE.value, allScope);
        List<PsiClass> classes = ClassInheritorsSearch.search(webPagePsiClass).findAll()
                .stream().collect(Collectors.toList());

        System.out.println("Classes:");
        classes.forEach(c -> System.out.println("*" + c.getQualifiedName()));

        // Start Analyzer of JDI Project structure
        // ...

        // Some tests
        // ...
    }
}
