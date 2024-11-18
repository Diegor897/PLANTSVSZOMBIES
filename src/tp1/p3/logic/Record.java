package tp1.p3.logic;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import tp1.p3.control.exceptions.RecordException;

public class Record {

	private static final String FILENAME = "record.txt";
	
	private static final String WRITE_ERROR_MSG = "Exception writing file";
	
	private static final String READ_ERROR_MSG = "Exception reading file";
	
	
	public static void save(int score, String level) throws RecordException {
		BufferedReader inChars = null;
		BufferedWriter outChars = null;
		StringBuffer inputBuffer = new StringBuffer();
		boolean found = false;
		
		try {
			inChars = new BufferedReader(new FileReader(FILENAME));
			String l;
			
			l = inChars.toString();
			
			while ((l = inChars.readLine()) != null) {
				if (l.contains(level)) {
					found = true;
					l = (level + ":" + score);
				}
				
				inputBuffer.append(l);
				inputBuffer.append('\n');
			}
			
			inChars.close();
			
			if (!found) {
				l = (level + ":" + score);
				inputBuffer.append(l);
				inputBuffer.append('\n');
			}
			
			outChars = new BufferedWriter(new FileWriter(FILENAME));
			outChars.write(inputBuffer.toString());
			
		} catch (IOException ex) {
			
			throw new RecordException(String.format("[ERROR]: %s", WRITE_ERROR_MSG));
			
		} finally {
			if (inChars != null) {
				try {
					inChars.close();
				} catch (IOException ex) {
					throw new RecordException(String.format("[ERROR]: %s", WRITE_ERROR_MSG));
				}
			}
			
			if (outChars != null) {
				try {
					outChars.close();
				} catch (IOException ex) {
					throw new RecordException(String.format("[ERROR]: %s", WRITE_ERROR_MSG));
				}
			}
		}
	}
	
	public static int load(String level) throws RecordException {
		BufferedReader inChars = null;
		boolean found = false;
		int record = 0;
		
		try {
			inChars = new BufferedReader(new FileReader(FILENAME));
			String l;
			
			l = inChars.toString();
			
			while (((l = inChars.readLine()) != null) && !found) {
				if (l.contains(level)) {
					found = true;
					record = Integer.parseInt(l.split(":")[1]);
				}
			}
			
			if (!found) {
				record = 0;
			}
			
			return record;

		} catch (FileNotFoundException ex) {
			
			throw new RecordException(String.format("[ERROR]: %s", ex.toString()));
			
		} catch (IOException ex) {
			
			throw new RecordException(String.format("[ERROR]: %s", READ_ERROR_MSG));
			
		} finally {
			if (inChars != null) {
				try {
					inChars.close();
				} catch (IOException ex) {
					throw new RecordException(String.format("[ERROR]: %s", READ_ERROR_MSG));
				}
			}
		}
	}
}