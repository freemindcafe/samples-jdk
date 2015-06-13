/**
 * <p>
 * log4j uses a hierarchy of loggers. Log messages are send to all the ancestors's appenders.
 * </p>
 * <p>
 * Using additivity we can turn off this nature.
 * </p>
 * <p>
 * TRACE > DEBUG > INFO > WARN > ERROR > FATAL > OFF
 * </p>
 * <p>
 * For this example the log file is generated with in the package directory.
 * </p>
 * <ul>
 * <li>FooBeans's appender has been set and it's additivity is false. Hence it's log messages will go in FileLog appender only and not in stdout appender.</li>
 * <li>BarBeans's appender has been set and additivity is false. Hence it's log messages will go in FileLog appender only and not in stdout appender.</li>
 * <li>App does not have any appender and additivity is by default true. It's messages will go to rootLogger's appenders stdout and FileLog.</li>
 * </ul>
 */
package com.freemindcafe.apache.log4j.sample1;