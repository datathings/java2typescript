package org.kevoree.modeling.java2typescript.mavenplugin;

import org.apache.maven.artifact.Artifact;
import org.kevoree.modeling.java2typescript.FlatJUnitGenerator;
import org.kevoree.modeling.java2typescript.SourceTranslator;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.plugins.annotations.ResolutionScope;
import org.apache.maven.project.MavenProject;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;

@Mojo(name = "generate", defaultPhase = LifecyclePhase.PROCESS_SOURCES, requiresDependencyResolution = ResolutionScope.COMPILE_PLUS_RUNTIME)
public class CompilePlugin extends AbstractMojo {

    /**
     * Src file
     */
    @Parameter
    private File source;

    /**
     * Target directory
     */
    @Parameter
    private File target;

    /**
     * Target directory
     */
    @Parameter(defaultValue = "${project.artifactId}")
    private String projectName;

    /**
     * The maven project.
     */
    @Parameter(defaultValue = "${project}", required = true, readonly = true)
    private MavenProject project;


    @Parameter
    private boolean flatJUnit = false;


    @Override
    public void execute() throws MojoExecutionException, MojoFailureException {
        source.mkdirs();
        target.mkdirs();
        String flatJunitGenDir = null;

        if(flatJUnit) {
            flatJunitGenDir = Paths.get(Paths.get(project.getBuild().getOutputDirectory()).getParent().toString(), "gen-jstest").toString();
            FlatJUnitGenerator testGenerator = new FlatJUnitGenerator();
            testGenerator.generate(source, new File(flatJunitGenDir + File.separator + "gentest"));
        }


        SourceTranslator sourceTranslator = new SourceTranslator();
        for (Artifact a : project.getDependencyArtifacts()) {
            File file = a.getFile();
            if (file != null) {
                sourceTranslator.getAnalyzer().addClasspath(file.getAbsolutePath());
                getLog().info("Add to classpath " + file.getAbsolutePath());
            }
        }
        try {
            sourceTranslator.translateSources(source.getPath(), target.getPath(), projectName);
            if(flatJUnit) {
                sourceTranslator.translateSources(new File(flatJunitGenDir).getPath(), target.getPath(), "TestRunner");
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new MojoExecutionException(e.getMessage());
        }
    }

}