import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

//mkfifo filename (mkfifo /tmp/sbfifo)
public class ReadFIFOJava {
    interface Callback<T> {
        void onMessage(T t);
        void onStop();
    }
    
	private static ReadFIFOJava readFIFOJava;	

	private final static String defFilePath = "/tmp/sbfifo";
	private String filePath = defFilePath;
	private File  f_pipe;
	RandomAccessFile raf;
	
	public static ReadFIFOJava getInstance() {
		return getInstance(defFilePath);
	}
	public static ReadFIFOJava getInstance(String filepath) {
       if (readFIFOJava == null) {
           synchronized (ReadFIFOJava.class) {
               if (readFIFOJava == null) {
            	   readFIFOJava = new ReadFIFOJava(filepath);
               }
           }
       }
       return readFIFOJava;
	}
	
	public ReadFIFOJava(String filePath) {
		setFilepath(filePath);
	}
	
	private void reAccessFile() throws FileNotFoundException {
		if(f_pipe==null) setFilepath(filePath);
		if(raf==null) raf = new RandomAccessFile(f_pipe, "r");
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
   
   
	public void readMessage(Callback<String> callback) throws FileNotFoundException, IOException {
		reAccessFile();
		String line=null;  
		for( ;; ){
			line=raf.readLine();                  
			if( line!=null && !line.equals("stop") ) {
				if(callback!=null) callback.onMessage(line);
			}else{
				if(callback!=null) callback.onStop();
				break; //stop reading loop
			}
		}
		closeFile();
	}
	
   
   public static void main(String[] args){	   
	   try {
		   ReadFIFOJava readFIFOJava = ReadFIFOJava.getInstance();
		   readFIFOJava.readMessage(new Callback<String>() {
			   @Override
			   public void onMessage(String msg) {
				   System.out.println(msg);
			   }
			   
			   @Override
			   public void onStop() {
				   System.out.println("onStop");
			   }			   
		   });
	   } catch (FileNotFoundException e) {
		   e.printStackTrace();
	   } catch (IOException e) {
		   e.printStackTrace();
	   }

   }

}
