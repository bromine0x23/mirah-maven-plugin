package bromine0x23.mirah;

import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.plugins.annotations.ResolutionScope;

import java.io.File;
import java.util.List;

/**
 * Compiles Mirah source files
 */
@Mojo(name = "compile", defaultPhase = LifecyclePhase.COMPILE, threadSafe = true, requiresDependencyResolution = ResolutionScope.COMPILE)
public class MirahCompilerMojo extends AbstractMirahMojo {

	/**
	 * Project classpath.
	 */
	@Parameter(defaultValue = "${project.compileClasspathElements}", required = true, readonly = true)
	private List<String> classpathElements;

	/**
	 * The source directories containing the sources to be compiled.
	 */
	@Parameter(defaultValue = "${basedir}/src/main/mirah", required = true)
	private File sourceDirectory;

	/**
	 * The directory for compiled classes.
	 */
	@Parameter(defaultValue = "${project.build.outputDirectory}", property = "mirah.output-directory", required = true)
	private File outputDirectory;

	/**
	 * Directory where macro class files should be saved.
	 */
	@Parameter(readonly = true)
	private List<String> macroClasspathElements;

	/**
	 * Directory where macro class files should be saved.
	 */
	@Parameter
	private File macroOutputDirectory;

	public void execute() throws MojoExecutionException {
		executeMirahCompiler();
	}

	@Override
	protected List<String> getClasspathElements() {
		return classpathElements;
	}

	@Override
	protected List<String> getMacroClasspathElements() {
		return macroClasspathElements;
	}

	@Override
	protected File getSourceDirectory() {
		return sourceDirectory;
	}

	@Override
	protected File getMacroOutputDirectory() {
		return macroOutputDirectory;
	}

	@Override
	protected File getOutputDirectory() {
		return outputDirectory;
	}
}
