/**
 * \brief Default ReflectionProvider skips constructor code or to be more detailed - the object initializer code.
 * 
 * If we do not specify a ReflectionProvider xstream tries to find the best reflection provider. 
 * But the best ReflectionProvider for xstream might not be the best for you, because it normally selects the Sun14ReflectionProvider.
 * 
 * The Sun14ReflectionProvider uses the same instantiation strategy as the java serialization mechanism and that means that it skips constructor code 
 * or to be more detailed - the object initializer code.
 * 
 * http://stackoverflow.com/questions/16060804/set-default-value-for-fields-not-in-xml-in-xstream
 * 
 */
package com.freemindcafe.serialization.sample5;