package edu.winona.cs.log;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JOptionPane;

public class Output {
    private Logger logger;
    private static final String LOGURL = "output.log";
    
	public Output(String className) {
    	logger = Logger.getLogger(className);
    }
    
    /**
     * Used to log exceptions.
     */
    public void log(Exception ex, LogLevel level, String msg){
        FileHandler fh = null;
        try {
            fh = new FileHandler(LOGURL,true);
            fh.setFormatter(new DebugFormatter());
            logger.addHandler(fh);
            switch (level) {
                case SEVERE:
                    logger.log(Level.SEVERE, msg, ex);
                    if(!msg.equals(""))
                        JOptionPane.showMessageDialog(null,msg,
                            "Error", JOptionPane.ERROR_MESSAGE);
                    break;
                case WARNING:
                    logger.log(Level.WARNING, msg, ex);
                    if(!msg.equals(""))
                        JOptionPane.showMessageDialog(null,msg,
                            "Warning", JOptionPane.WARNING_MESSAGE);
                    break;
                case INFO:
                    logger.log(Level.INFO, msg, ex);
                    if(!msg.equals(""))
                        JOptionPane.showMessageDialog(null,msg,
                            "Info", JOptionPane.INFORMATION_MESSAGE);
                    break;
                case CONFIG:
                    logger.log(Level.CONFIG, msg, ex);
                    break;
                case FINE:
                    logger.log(Level.FINE, msg, ex);
                    break;
                case FINER:
                    logger.log(Level.FINER, msg, ex);
                    break;
                case FINEST:
                    logger.log(Level.FINEST, msg, ex);
                    break;
                default:
                    logger.log(Level.CONFIG, msg, ex);
                    break;
            }
        } catch (IOException | SecurityException ex1) {
            logger.log(Level.SEVERE, null, ex1);
        } finally{
            if(fh!=null)fh.close();
        }
    }
    
    /**
     * Used to log other messages.
     */
    public void log(LogLevel level, String msg) {
    	FileHandler fh = null;
    	try {
            fh = new FileHandler(LOGURL,true);
            fh.setFormatter(new DebugFormatter());
            logger.addHandler(fh);
            switch (level) {
            case SEVERE:
            	logger.log(Level.SEVERE, msg);
            	break;
            case WARNING:
            	logger.log(Level.WARNING, msg);
            	break;
            case INFO:
            	logger.log(Level.INFO, msg);
            	break;
            case CONFIG:
            	logger.log(Level.CONFIG, msg);
            	break;
            case FINE:
            	logger.log(Level.FINE, msg);
            	break;
            case FINER:
            	logger.log(Level.FINER, msg);
            	break;
            case FINEST:
            	logger.log(Level.FINEST, msg);
            	break;
            default:
            	logger.log(Level.CONFIG, msg);
            	break;
            }
        } catch (IOException | SecurityException ex1) {
            logger.log(Level.SEVERE, null, ex1);
        } finally{
            if(fh!=null)fh.close();
        }
    }
}//End class
