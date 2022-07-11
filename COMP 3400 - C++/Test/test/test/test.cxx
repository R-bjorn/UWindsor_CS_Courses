#include <iostream>

using namespace std;

struct foo
{
    foo() // default constructor
    {
        cout << "DEFAULT: foo() where this == " << this << '\n';
    }

    foo(foo const& f) // copy constructor
    {
        cout << "COPY: foo(" << &f << ") where this == " << this << '\n';
    }

    foo(foo&& f) // move constructor
    {
        cout << "MOVE: foo(" << &f << ") where this == " << this << '\n';
    }

    foo& operator =(foo const& f) // copy assignment
    {
        cout << "COPY_ASSIGN: foo(" << &f << ") where this == " << this << '\n';
        return *this;
    }

    foo& operator =(foo&& f) // move assignment
    {
        cout << "MOVE_ASSIGN: foo(" << &f << ") where this == " << this << '\n';
        return *this;
    }

    ~foo() // destructor
    {
        cout << "~foo() where this == " << this << '\n';
    }
};

foo silly(foo const& a)
{
    cout << "silly start\n";
    foo retval;
    cout << "silly almost end\n";
    return retval;
}

int main()
{
    foo f;
    silly(f);
}
