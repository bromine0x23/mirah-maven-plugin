package bromine0x23.mirah;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugins.annotations.Parameter;
import org.codehaus.plexus.util.StringUtils;
import org.mirah.tool.Mirahc;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractMirahMojo extends AbstractMojo {

	private static final String DEFAULT_TARGET = "1.6";

	/**
	 * Verbose logging.
	 */
	@Parameter(defaultValue = "false")
	private boolean verbose;

	/**
	 * File encoding.
	 */
	@Parameter(property = "project.build.sourceEncoding")
	private String encoding;

	/**
	 * Emit JVM bytecode targeting specified JVM version (1.5, 1.6, 1.7)
	 */
	@Parameter(property = "maven.compiler.target", defaultValue = DEFAULT_TARGET, required = true)
	private String target;

	protected void executeMirahCompiler() throws MojoExecutionException {
		if (!getOutputDirectory().exists()) {
			//noinspection ResultOfMethodCallIgnored
			getOutputDirectory().mkdirs();
		}

		List<String> arguments = getCompilerArguments();

		if (!getSourceDirectory().exists()) {
			getLog().info("Source directory: " + getSourceDirectory().getAbsolutePath() + " does not exists or not accessible. Skip mirahc.");
		} else {
			arguments.add(getSourceDirectory().getAbsolutePath());
			mojoCompile(arguments);
		}
	}

	protected void mojoCompile(List<String> arguments) throws MojoExecutionException {
		if (getLog().isDebugEnabled()) {
			getLog().debug("Arguments: " + arguments);
		}
		Mirahc mirahc = new Mirahc();
		int    result = mirahc.compile(arguments.toArray(new String[arguments.size()]));
		if (result != 0) {
			throw new MojoExecutionException("Compilation failed with arguments: " + arguments);
		}
	}

	private List<String> getCompilerArguments() {
		List<String> arguments = new ArrayList<String>();

		if (verbose) {
			arguments.add("--verbose");
		}

		if (encoding != null) {
			arguments.add("--encoding");
			arguments.add(encoding);
		}

		arguments.add("--jvm");
		arguments.add(target);

		arguments.add("--classpath");
		arguments.add(StringUtils.join(getClasspathElements().iterator(), File.pathSeparator));

		arguments.add("--dest");
		arguments.add(getOutputDirectory().getAbsolutePath());

		if (getMacroOutputDirectory() != null) {
			arguments.add("--macro-dest");
			arguments.add(getMacroOutputDirectory().getAbsolutePath());
		}

		if (getMacroClasspathElements() != null) {
			arguments.add("--macroclasspath");
			arguments.add(StringUtils.join(getMacroClasspathElements().iterator(), File.pathSeparator));
		}

		return arguments;
	}

	protected abstract List<String> getClasspathElements();

	protected abstract List<String> getMacroClasspathElements();

	protected abstract File getOutputDirectory();

	protected abstract File getMacroOutputDirectory();

	protected abstract File getSourceDirectory();
}
