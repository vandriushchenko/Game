package org.diosoft.javacourses;

public class Person implements Comparable<Person>{
    private final String name;
    private final String surname;
    private final int age;
    private final int phone;

    private Person(Builder builder){
        this.name = builder.name;
        this.surname = builder.surname;
        this.age = builder.age;
        this.phone = builder.phone;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public int getAge() {
        return age;
    }

    public int getPhone() {
        return phone;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Person person = (Person) o;

        if (age != person.age) return false;
        if (phone != person.phone) return false;
        if (name != null ? !name.equals(person.name) : person.name != null) return false;
        return !(surname != null ? !surname.equals(person.surname) : person.surname != null);
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (surname != null ? surname.hashCode() : 0);
        result = 31 * result + age;
        result = 31 * result + phone;
        return result;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", age=" + age +
                ", phone=" + phone +
                '}';
    }

    @Override
    public int compareTo(Person o) {
        if(getSurname().compareTo(o.getSurname()) != 0)
            return getSurname().compareTo(o.getSurname());
        return getName().compareTo(o.getName());
    }

    public static class Builder{
        private String name;
        private String surname;
        private int age;
        private int phone;

        public Builder(){}

        public Builder(Person origin){
            this.name = origin.name;
            this.surname = origin.surname;
            this.age = origin.age;
            this.phone = origin.phone;
        }

        public Builder name(String name){
            this.name = name;
            return this;
        }

        public Builder surname(String surname){
            this.surname = surname;
            return this;
        }

        public Builder age(int age){
            this.age = age;
            return this;
        }

        public Builder phone(int phone){
            this.phone = phone;
            return this;
        }

        public Person build(){
            return new Person(this);
        }
    }
}
