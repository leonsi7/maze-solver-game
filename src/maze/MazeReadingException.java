package maze;
/**
 * Classe d'exception qui est lev�e � chaque fois qu'il y a un probl�me d'ex�cution de l'application (qui lui est propre)
 * @author L�on
 *
 */
public class MazeReadingException extends Exception {
	
	public MazeReadingException(String errorMessage, String fileName, int lineNumber, int columnNumber) {
        super(errorMessage + " for the file : " + fileName + ", at the line : "+ lineNumber+ " in the column : "+ columnNumber);
        
}
	public MazeReadingException(String errorMessage, String fileName, int lineNumber) {
		super(errorMessage + " for the file : " + fileName + ", at the line : "+ lineNumber);
	}
	public MazeReadingException(String errorMessage, String fileName) {
		super(errorMessage+" for the file : "+ fileName);
	}
	public MazeReadingException(String errorMessage) {
		super(errorMessage);
	}
	}
