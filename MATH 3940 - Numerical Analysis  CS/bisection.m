function [root , i] = bisection(f,a,b,tolerance,maxiter)
    if f(a)*f(b)>0 
        disp('Wrong choice for a and b');
    else
        root = (a + b)/2;
        err = 1;
        i = 1;
        while err > tolerance && i<=maxiter
            % checking at which side loop lies
            if f(a)*f(root)<0 
                b = root;
            else
                a = root;          
            end
            % root to mid value
            root = (a + b)/2;
            % fetching error
            err = abs(f(root));
            % incrementing counter
            i = i+1;
        end
    end
end