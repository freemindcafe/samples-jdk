/**
 * \brief rmic and rmiregistry is used. This one does not uses SecurityManager.
 * 
 * <h1>RMI samples.</h1>
 * <p>
 * http://www.ejbtutorial.com/java-rmi/a-step-by-step-implementation-tutorial-for-java-rmi
 * </p>
 * <p>
 * This one does not uses SecurityManager.
 * </p>
 * <ul>
 * <li>Open command line shell.</li>
 * <li>cd ${project_loc}\build\classes</li>
 * <li>rmic com.freemindcafe.rmi.sample1.server.Addition</li>
 * <li>start rmiregistry</li>
 * <li>Use AdditionServer.launch file to launch the server</li>
 * <li>Use AdditionClient.launch to launch the client</li>
 * </ul>
 */
package com.freemindcafe.rmi.sample1;