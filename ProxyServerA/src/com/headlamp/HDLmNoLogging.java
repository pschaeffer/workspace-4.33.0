package com.headlamp;
import org.eclipse.jetty.util.log.Logger;
/**
 * HDLmNoLogging short summary.
 *
 * This class was created just to stop any visible logging while 
 * running some of the unit tests. Some of the unit tests produce
 * visible (red even) log messages. This class replaces the standard
 * logger (which writes to standard error) and does nothing. 
 *
 * @version 1.0
 * @author Peter
 */
public class HDLmNoLogging implements Logger {
	@Override 
	public String getName() { 
		return "no"; 
  }
	@Override
	public void debug(String msg, long value) {}   
  @Override 
  public void debug(String msg, Object... args) {}
  @Override 
  public void debug(String msg, Throwable thrown) {}
  @Override 
  public void debug(Throwable thrown) {}
  @Override 
  public Logger getLogger(String name) { 
  	return this; 
  }
  @Override 
  public void ignore(Throwable ignored) {}
  @Override 
  public void info(String msg, Object... args) {}
  @Override 
  public void info(String msg, Throwable thrown) {}
  @Override 
  public void info(Throwable thrown) {}
  @Override 
  public boolean isDebugEnabled() { return false; }
  @Override 
  public void setDebugEnabled(boolean enabled) {}
  @Override 
  public void warn(String msg, Object... args) {}
  @Override 
  public void warn(String msg, Throwable thrown) {}
  @Override 
  public void warn(Throwable thrown) {}
}