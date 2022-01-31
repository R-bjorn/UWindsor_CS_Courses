function [p0,maxiter] = gseid2(eq,p0,tol,itr)
  n = length(p0);
  for k=1:itr
      X=p0;
      for j=1:n
          A = feval(eq,X);
          X(j) = A(j);
      end
      err= abs(norm(X-p0));
      relerr = err/(norm(X)+eps);
      p0 = X;
      maxiter = k;
      if(err<tol||relerr<tol)
          break;
      end
  end