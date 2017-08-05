package bromine0x23.mirah;

import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.plugins.annotations.ResolutionScope;

import java.io.File;
import java.util.List;

/**
 * Compiles Mirah test source files
 */
@Mojo(name = "test-compile", defaultPhase = LifecyclePhase.TEST_COMPILE, threadSafe = true, requiresDependencyResolution = ResolutionScope.TEST)
public class MirahTestCompilerMojo extends AbstractMirahMojo {

	/**
	 * Set this to 'true' to bypass unit tests entirely.
	 */
	@Parameter(property = "maven.test.skip")
	private boolean skip;

	/**
	 * Project classpath.
	 */
	@Parameter(defaultValue = "${project.testClasspathElements}", required = true, readonly = true)
	private List<String> classpathElements;

	/**
	 * Directory where macro class files should be saved.
	 */
	@Parameter(readonly = true)
	private List<String> macroClasspathElements;

	/**
	 * The directory for compiled classes.
	 */
	@Parameter(defaultValue = "${project.build.testOutputDirectory}", required = true)
	private File outputDirectory;

	/**
	 * Directory where macro class files should be saved.
	 */
	@Parameter
	private File macroOutputDirectory;

	/**
	 * The source directories containing the sources to be compiled.
	 */
	@Parameter(defaultValue = "${basedir}/src/test/mirah", required = true)
	private File sourceDirectory;

	public void execute() throws MojoExecutionException {
		if (skip) {
			getLog().info("skiping mirah tests compilation");
		} else {
			executeMirahCompiler();
		}
	}

	@Override
	protected List<String> getClasspathElements() {
		return classpathElements;
	}

	protected List<String> getMacroClasspathElements() {
		return macroClasspathElements;
	}

	@Override
	protected File getOutputDirectory() {
		return outputDirectory;
	}

	protected File getMacroOutputDirectory() {
		return macroOutputDirectory;
	}

	@Override
	protected File getSourceDirectory() {
		return sourceDirectory;
	}

}
