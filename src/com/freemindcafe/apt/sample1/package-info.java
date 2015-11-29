/**
 * \brief Annotation Processing Tool examples.
 * 
 * <p>
 * Annotation processing tools run as part of java compilation. APT provides the hook to modify or generate the source during the compilation phase.
 * </p>
 * <ul>
 * <li>
 * For that to happen one will have to change the compiler options in the eclipse project.
 * \image html image1.png
 * </li>
 * <li>
 * Processor code in our case SillyProcessor should be exported in a seperate jar and needs to be added in the project.
 * \image html image2.png
 * </li>
 * <li>
 * Create javax.annotation.processing.Processor file under META-INF/services and add our processor in it. 
 * \image html image3.png
 * </li>
 * </ul>
 * 
 * 
 *
 * 
 */
package com.freemindcafe.apt.sample1;