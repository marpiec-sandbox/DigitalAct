/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.digitalact.index;

import javax.inject.Named;
import java.io.Serializable;
import org.springframework.context.annotation.Scope;

/**
 * Backing bean obsługujący stronę główną.
 * @author Marcin
 */
@Named
@Scope("request")
public class IndexBacking implements Serializable {

}
