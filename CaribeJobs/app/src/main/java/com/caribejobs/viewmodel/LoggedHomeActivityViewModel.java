package com.caribejobs.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.caribejobs.model.Profession;
import com.caribejobs.model.Reference;
import com.caribejobs.model.ReferencePicture;
import com.caribejobs.model.Schedule;
import com.caribejobs.model.User;
import com.caribejobs.model.UserLogin;
import com.caribejobs.model.UserProfession;
import com.caribejobs.model.WorkZone;
import com.caribejobs.model.address.Utility;

public class LoggedHomeActivityViewModel extends ViewModel {
    private MutableLiveData<User> user = new MutableLiveData<>();
    private MutableLiveData<Boolean> logged = new MutableLiveData<>();

    public LoggedHomeActivityViewModel() {
        user.setValue(UserLogin.getUserLogged());
        logged.setValue(true);
    }

    public MutableLiveData<User> getUser() {
        return this.user;
    }

    public MutableLiveData<Boolean> getLogged() {
        return logged;
    }

    private User createTempUser() {
        User user = new User();
        user.setUsername("LuisMJ");
        user.setFirstname("Luis");
        user.setLastname("Molina");
        user.setLastname2("Juárez");
        user.setEmail("luisfermjua@gmail.com");
        user.setPhonenumber1("61638663");
        user.setPhonenumber2("63148694");
        user.setBirthday("20-11-1997");
        user.setProfilePicture("https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcS6zes53m4a_2VLTcmTn_bHk8NO5SkuWfcQbg&usqp=CAU");
        user.setAddress("Limón", "Matina", "Matina");
        user.addProfession(new UserProfession(new Profession(1, "Ingeniero1"), 2, "ingun", 12000));
        user.addProfession(new UserProfession(new Profession(2, "Ingeniero2"), 2, "ingun", 12000));
        user.addProfession(new UserProfession(new Profession(3, "Ingeniero3"), 2, "ingun", 0));
        user.getProfessions().get(2).setaNegociar(true);
        user.getProfessions().get(0).addReferencePicture(new ReferencePicture(1, "https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcS6zes53m4a_2VLTcmTn_bHk8NO5SkuWfcQbg&usqp=CAU"));
        user.addWorkZone(new WorkZone("Limón", "Matina"));
        user.addWorkZone(new WorkZone("Limón", "Limón"));
        user.addReference(new Reference("IngAnt", "michelle", "Alvarado", "Zúñiga", "63148694"));
        Schedule schedule = new Schedule();
        schedule.setAllDays(true);

        user.setSchedule(schedule);
        User.userLogged = user;
        return user;
    }
}
