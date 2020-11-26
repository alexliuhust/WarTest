import com.wartest.view.LogInFrm;

public class AppEntry {

	public static void main(String[] args) {
		try {
			LogInFrm frame = new LogInFrm("", "");
			frame.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
