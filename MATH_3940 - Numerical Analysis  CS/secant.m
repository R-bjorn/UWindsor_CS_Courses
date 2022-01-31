function [root, i] = secant(f,p0,p1,tolerance,maxiter)
    % checking if iterval is correct or not
    if f(p0)*f(p1)>0 
        disp('Invalid interval !!!')
    else
        % defining error to high value
        err=1;
        % defining loop counter 
        i = 1;
        while err > tolerance && i<= maxiter
            % fetching x2
            x2=(p0*f(p1)-p1*f(p0))/(f(p1)-f(p0));
            % changing p0 and p1
            p0=p1;        
            p1=x2;
            % error
            err=abs(p1-p0);
            % changing root vale
            root=x2;            
        end
    end
end