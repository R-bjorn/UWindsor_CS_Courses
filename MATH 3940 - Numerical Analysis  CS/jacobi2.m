function [p,maxiter] = jacobi2(eq,p0,tol,itr)
  n = length(p0);
  for k=1:itr
      for j=1:n
          X = feval(eq,p0);
      end
      err= (norm(X-p0));
      relerr = err/norm(X);
      p0 = X;
      maxiter = k;
      if(err<tol||relerr<tol)
          break;
      end
  end
  p = p0';