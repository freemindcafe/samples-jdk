/**
 * \brief
 * 
 * <ul>
 * <li>With out enabling SecurityManager policy file does not come in the picture.</li>
 * <li>Once SecurityManager is enabled, protection domains, permissions etc comes into life.</li>
 * <pre>
 * Protection Domain - CodeSource
 * Permission - A single permission
 * PermissionCollection - collection of homogeneous permissions (all File permisisons etc)
 * Permissions - Heterogeneous collection
 * Policy file
 * </pre>
 * </ul>
 * 
 *  - Use a custom policy file
 *  - AccessController use
 *  - doPrivilidged use
 *  - class loader and protection domain
 *  - jar signing use
 *  
 * http://www.smartjava.org/content/how-analyze-java-ssl-errors
 */
package com.freemindcafe.security;