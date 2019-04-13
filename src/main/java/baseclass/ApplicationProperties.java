package baseclass;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ApplicationProperties extends Properties {
	private static final long serialVersionUID = 1L;
	private static ApplicationProperties props = null;

	private ApplicationProperties() throws IOException {
		load(new FileInputStream("config/config.properties"));
	}

	public static synchronized ApplicationProperties getInstance() {
		if (props == null) {
			try {
				props = new ApplicationProperties();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return props;
	}
}
