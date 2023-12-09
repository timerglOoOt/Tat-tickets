<<<<<<< HEAD
package tat_tickets.services.impl;

import tat_tickets.services.SignOutService;

import javax.servlet.http.HttpServletRequest;

public class SignOutServiceImpl implements SignOutService {
    @Override
    public void closeSession(HttpServletRequest request) {
        request.getSession().removeAttribute("user");
    }
}
=======
package tat_tickets.services.impl;

import tat_tickets.services.SignOutService;

import javax.servlet.http.HttpServletRequest;

public class SignOutServiceImpl implements SignOutService {
    @Override
    public void closeSession(HttpServletRequest request) {
        request.getSession().removeAttribute("user");
    }
}
>>>>>>> d23f224 (feat: add done project)
