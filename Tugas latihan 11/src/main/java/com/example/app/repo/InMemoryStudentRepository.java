package com.example.app.repo;
import com.example.app.model.Student;
import java.util.*;
public class InMemoryStudentRepository implements StudentRepository{
    private final Map<String,Student> store=new HashMap<>();
    public boolean existsByNpm(String npm){return store.containsKey(npm);}
    public void save(Student s){store.put(s.getNpm(),s);}
    public Optional<Student> findByNpm(String npm){return Optional.ofNullable(store.get(npm));}
    public List<Student> getAll(){return new ArrayList<>(store.values());}
    public void delete(String npm){store.remove(npm);}
}