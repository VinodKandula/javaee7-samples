/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 *
 * Copyright (c) 2013 Oracle and/or its affiliates. All rights reserved.
 *
 * The contents of this file are subject to the terms of either the GNU
 * General Public License Version 2 only ("GPL") or the Common Development
 * and Distribution License("CDDL") (collectively, the "License").  You
 * may not use this file except in compliance with the License.  You can
 * obtain a copy of the License at
 * https://glassfish.dev.java.net/public/CDDL+GPL_1_1.html
 * or packager/legal/LICENSE.txt.  See the License for the specific
 * language governing permissions and limitations under the License.
 *
 * When distributing the software, include this License Header Notice in each
 * file and include the License file at packager/legal/LICENSE.txt.
 *
 * GPL Classpath Exception:
 * Oracle designates this particular file as subject to the "Classpath"
 * exception as provided by Oracle in the GPL Version 2 section of the License
 * file that accompanied this code.
 *
 * Modifications:
 * If applicable, add the following below the License Header, with the fields
 * enclosed by brackets [] replaced by your own identifying information:
 * "Portions Copyright [year] [name of copyright owner]"
 *
 * Contributor(s):
 * If you wish your version of this file to be governed by only the CDDL or
 * only the GPL Version 2, indicate your decision by adding "[Contributor]
 * elects to include this software in this distribution under the [CDDL or GPL
 * Version 2] license."  If you don't indicate a single choice of license, a
 * recipient has the option to distribute your version of this file under
 * either the CDDL, the GPL Version 2 or to extend the choice of license to
 * its licensees as provided above.  However, if you add GPL Version 2 code
 * and therefore, elected the GPL Version 2 license, then the option applies
 * only if the new code is made subject to such option by the copyright
 * holder.
 */
package org.javaee7.samples.jta;

import javax.enterprise.context.ContextNotActiveException;
import javax.inject.Inject;
import javax.transaction.Transactional;

/**
 * @author Arun Gupta
 */
public class MyTransactionalBean {

    @Inject
    MyDataBean bean1;
    
    @Inject
    MyDataBean bean2;

    @Transactional
    public void scenario1() {
        System.out.println("Scenario 1: Bean injected twice, same id");
        System.out.println(bean1.getId());
        System.out.println(bean2.getId());
    }

    @Transactional
    public void scenario2() {
        System.out.println("Scenario 2: Repeat of Scenario 1, different transaction, different ids");
        System.out.println(bean1.getId());
        System.out.println(bean2.getId());
    }

    public void scenario3() {
        System.out.println("Scenario 3: Bean outside a transaction");
        try {
            bean1.getId();
        } catch (ContextNotActiveException ex) {
            System.out.println("Got expected ContextNotActiveException");
        }
    }
}
