import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;

// mkfifo filename (mkfifo /tmp/sbfifo) 
public class WriteFIFOJava {

	private static WriteFIFOJava writeFIFOJava;
	
	private final static String defFilePath = "/tmp/sbfifo";
	private String filePath = defFilePath;
	private File  f_pipe;
	RandomAccessFile raf;
	
	public static WriteFIFOJava getInstance() {
		return getInstance(defFilePath);
	}
	public static WriteFIFOJava getInstance(String filepath) {
        if (writeFIFOJava == null) {
            synchronized (WriteFIFOJava.class) {
                if (writeFIFOJava == null) {
                		writeFIFOJava = new WriteFIFOJava(filepath);
                }
            }
        }
        return writeFIFOJava;
	}
	
	public WriteFIFOJava(String filePath) {
		setFilepath(filePath);
	}
	
	private void reAccessFile() throws FileNotFoundException {
		if(f_pipe==null) setFilepath(filePath);
		if(raf==null) raf = new RandomAccessFile(f_pipe, "rw");
	}
	
	private void closeFile() throws IOException {
		raf.close();
	}
	public String getFilepath() {
		return filePath;
	}

	public void setFilepath(String filePath) {
		if(filePath == null) {
			this.filePath = defFilePath;
		} else {
			this.filePath = filePath;
		}
		f_pipe = new File ( this.filePath );
	}
	
	public void writeMessage(String msg) throws FileNotFoundException, IOException {
		reAccessFile();
		raf.writeBytes(msg + "\n");
	}
	   
	public static void main(String[] args){
		try {
			WriteFIFOJava writeFIFOJava = WriteFIFOJava.getInstance();
			writeFIFOJava.writeMessage("WriteFIFOJava start");
			
			Scanner scanner = new Scanner(System.in);
			String readString = scanner.nextLine();
			while(readString!=null&&!readString.equals("stop")) {
				writeFIFOJava.writeMessage(readString);
				
				if (scanner.hasNextLine()) {
					readString = scanner.nextLine();
				} else {
					readString = null;
				}
			}
			writeFIFOJava.writeMessage(readString);
			writeFIFOJava.closeFile();
			scanner.close();
		    
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	 
}