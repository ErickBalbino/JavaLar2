package controllers.objetos;

public class FileReader {
	private String FileName;

	public FileReader(String FileName) {
		this.FileName = FileName;
	}
	
	public String getFileName() {
		return this.FileName;
	}
}
