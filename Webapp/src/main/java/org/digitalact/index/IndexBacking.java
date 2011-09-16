/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.digitalact.index;

import javax.annotation.PostConstruct;
import javax.inject.Named;
import java.io.Serializable;
import org.springframework.context.annotation.Scope;

/**
 * @author Marcin
 */
@Named
@Scope("request")
public class IndexBacking implements Serializable {

    @PostConstruct
    public void init() {

    }

}
