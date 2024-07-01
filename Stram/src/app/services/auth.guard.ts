import { Inject, inject } from '@angular/core';
import { CanActivateFn, Router } from '@angular/router';

export const authGuard: CanActivateFn = (route, state) => {

  const router : Router = inject(Router);

  if(localStorage.getItem("user") != null)
    return true;
  else
    return router.navigate(["/"]);
};

export const adminGuard : CanActivateFn = (route,state) => {

  const router : Router = inject(Router);

  if(Number(localStorage.getItem("type")) == 1)
    return true;
  else
    return router.navigate(["/"]);

};

export const NoAuthGuard : CanActivateFn = (route,state) => {

  const router : Router = inject(Router);

  if(localStorage.getItem("user") == null)
    return true;
  else
    return router.navigate(["/"]);
};
