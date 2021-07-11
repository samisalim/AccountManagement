/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package useraccounts.Exceptions;

/**
 *
 * @author Salim
 */
public class InvalidLoadException extends RuntimeException{
    
    private String filename;


        public InvalidLoadException(String filename) {
        super("filename could not be loaded" + filename);
        this.filename = filename;
        }
	/*
	* call super message: filename could not be loaded or data that could not be parsed. + accountsFile
	* assign parameter filename to attribute filename
	*/

	public InvalidLoadException(String filename, String message) {
        super("there is an issue parsing the contents of the file" + filename);
        this.filename = filename;
        }
	/*
	* call a super message "there is an issue parsing the contents of the file" +filename
	* assign parameter filename to attribute filename
	*/

  
    
    public String toString(){
        
        return this.getClass().getSimpleName() + " The File does't exist or can't parsing" + filename;
    }
    
}
