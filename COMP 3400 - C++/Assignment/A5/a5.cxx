#include <vector>
#include <memory>
#include <string>
#include <type_traits>
#include <utility>
#include <iostream>

using namespace std::literals;
using namespace std;

class animal
{
public:
	virtual ~animal() { }
	virtual std::string kind() const = 0;
};

using animal_ptr = std::unique_ptr<animal>;

template <typename T>
animal_ptr make_animal(T&& t)
{
	using type = std::remove_cvref_t<T>;
	return animal_ptr{ new type(std::forward<T>(t)) };
}	

// Creating cat class
class cat : virtual public animal
{
public:
	virtual ~cat() { }
	// overriding kind 
	string kind() const override {
		return "Felis catus"s;
	}
};

// Creating dog class
class dog : virtual public animal
{
// private stirng element declared
private: string data;

public:
	virtual ~dog() { }
	dog() = delete;
	dog(const dog&) = default;
	dog& operator= (const dog& d) {
		if (&d == this) {
			return *this;
		}
	}
	
	// Constructor
	dog(string const& str) : data(str) {}
		
	string kind() const override
	{
		return "Canis lupus familiaris"s;
	}

	string const& name()
	{
		return data;
	}
};

class dogcat : public virtual cat , public virtual dog
{

public:
	dogcat() : cat::cat(), dog::dog("hybrid") {}

	dogcat(dog const& d, cat const& c) : cat::cat(c), dog::dog(d) { }
	dogcat(const dogcat&) = default;
	dogcat& operator= (const dogcat&) = default;

	string kind() const override
	{
		return dog::kind() + " + " + cat::kind();
	}
};

dogcat operator +(dog const& d, cat const& c)
{
	return dogcat(d, c);
}

int main() 
{
	std::vector<animal_ptr> v;
	dog d("Fibo");
	cat c;
	v.push_back(make_animal(d));
	v.emplace_back(new cat);
	v.push_back(make_animal(d+c));

	for (animal_ptr const& e : v)
	{
		cout << e->kind() ;
		dog* p = dynamic_cast<dog*>(e.get());

		if (p != NULL) {
			cout << ", name = " << p->name() << '\n';
		}
		else {
			cout << '\n';
		}
	}
}