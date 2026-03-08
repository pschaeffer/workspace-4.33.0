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
	public void debug(final String msg, final long value) {}   
  @Override 
  public void debug(final String msg, final Object... args) {}
  @Override 
  public void debug(final String msg, final Throwable thrown) {}
  @Override 
  public void debug(final Throwable thrown) {}
  @Override 
  public Logger getLogger(final String name) { 
  	return this; 
  }
  @Override 
  public void ignore(final Throwable ignored) {}
  @Override 
  public void info(final String msg, final Object... args) {}
  @Override 
  public void info(final String msg, final Throwable thrown) {}
  @Override 
  public void info(final Throwable thrown) {}
  @Override 
  public boolean isDebugEnabled() { return false; }
  @Override 
  public void setDebugEnabled(final boolean enabled) {}
  @Override 
  public void warn(final String msg, final Object... args) {}
  @Override 
  public void warn(final String msg, final Throwable thrown) {}
  @Override 
  public void warn(final Throwable thrown) {}
}