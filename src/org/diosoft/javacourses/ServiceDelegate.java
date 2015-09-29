package org.diosoft.javacourses;

public class ServiceDelegate {
    private final ArrayHelper arrayHelper;

    public ServiceDelegate(ArrayHelper arrayHelper) {
        this.arrayHelper = arrayHelper;
    }

    public Person[] merge(Person[] leftArray, Person[] rightArray) {
        checkForNullOrEmpty(leftArray);
        checkForNullOrEmpty(rightArray);
        return arrayHelper.merge(leftArray, rightArray);
    }

    private void checkForNullOrEmpty(Person[] persons) {

        for (Person person : persons) {
            if (person.getSurname() == null || person.getName() == null || person.getName().isEmpty() || person.getSurname().isEmpty()) {
                throw new IllegalStateException("Person contains null or empty mandatory field: " + person);
            }
        }
    }

    public boolean compareArrays(Person[] actual, Person[] expected) {
        return arrayHelper.compareArrays(actual, expected);
    }

    public static void main(String[] args) {
        Person [] left = {new Person.Builder().surname("surname1").build(),new Person.Builder().name("name2").surname("surname2").build()};
        Person [] right = {new Person.Builder().name("name1").surname("surname1").build(),new Person.Builder().name("name3").surname("surname3").build()};
        Person [] expectedMergeArray = {new Person.Builder().name("name1").surname("surname1").build(),new Person.Builder().name("name2").surname("surname2").build(),new Person.Builder().name("name3").surname("surname3").build()};
        ArrayHelper arrayHelper = new ArrayHelper();
        ServiceDelegate serviceDelegate = new ServiceDelegate(arrayHelper);
        System.out.println(serviceDelegate.compareArrays(serviceDelegate.merge(left, right), expectedMergeArray));
    }
}
