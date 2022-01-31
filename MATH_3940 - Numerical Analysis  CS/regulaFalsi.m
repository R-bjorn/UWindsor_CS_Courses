function [root, i] = regulaFalsi(f,a,b,tolerance,maxiter,epsilon)
    % looping maxiter times
    for i=1:maxiter
        % Calculating the value of function at a
        fa=f(a); 
        % Calculating the value of function at x1
        fb=f(b);
        % [a,b] is the interval of the root
        root=b-((b-a)/(fb-fa))*fb;
        % defining error
        err=abs(root-b);
        % checking the amount of error at each iteration
        if err<tolerance 
            break
        end
        % value at root
        f2=f(root);
        % fetching possible iterval of root
        
        if (fb)*(f2)<1
            % taking the next interval as[a,b] = [y,b]
            a=root;  
        else
            % taking the next interval as[a,b] = [a,y]
            b=root;
        end
    end
    % Displaying upto required decimal places
    root = root - rem(root,epsilon); 
end