package com.caribejobs.apidatabinding;

import com.apollographql.apollo.api.ResponseField;
import com.caribejobs.GetDireccionesQuery;
import com.caribejobs.GetProfessionsQuery;
import com.caribejobs.LoginQuery;
import com.caribejobs.model.Profession;
import com.caribejobs.model.Reference;
import com.caribejobs.model.ReferencePicture;
import com.caribejobs.model.Schedule;
import com.caribejobs.model.User;
import com.caribejobs.model.UserLogin;
import com.caribejobs.model.UserProfession;
import com.caribejobs.model.WorkZone;
import com.caribejobs.model.address.Canton;
import com.caribejobs.model.address.Provincia;

import java.sql.Ref;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ModelClassBuilder {

    public static UserLogin createUserFromLoginData(LoginQuery.Login loginData) {
        UserLogin userLogin = new UserLogin(loginData.username(), "");
        userLogin.setFirstname(loginData.firstname());
        userLogin.setLastname(loginData.lastname());
        userLogin.setLastname2(loginData.lastname2());
        userLogin.setEmail(loginData.email());
        userLogin.setPhonenumber1(loginData.phonenumber1());
        userLogin.setPhonenumber2(loginData.phonenumber2());
        userLogin.setProfilePicture(loginData.profilePicture());
        userLogin.setBirthday(loginData.birthday());
        userLogin.setProfessions(createUserProfessions(loginData.professions()));
        userLogin.setReferences(createUserReferences(loginData.references()));
        userLogin.setWorkZones(createUserWorkZones(loginData.workZones()));
        userLogin.setSchedule(createSchedule(loginData.availableSchedule()));
        return userLogin;
    }

    private static Schedule createSchedule(List<LoginQuery.AvailableSchedule> availableSchedule) {
        Schedule schedule = new Schedule();
        for (LoginQuery.AvailableSchedule day: availableSchedule) {
            if (day.day() == 0) {
                schedule.setSet(true);
                schedule.setStartTime(day.startTime());
                schedule.setEndTime(day.endTime());
            } else {
                schedule.getDays()[day.day()].setSet(true);
                schedule.getDays()[day.day()].setStartTime(day.startTime());
                schedule.getDays()[day.day()].setEndTime(day.endTime());
            }
        }
        System.out.println(schedule.toString());
        return schedule;
    }

    private static ArrayList<WorkZone> createUserWorkZones(List<LoginQuery.WorkZone> workZones) {
        ArrayList<WorkZone> workZones1 = new ArrayList<>();
        for (LoginQuery.WorkZone workZone: workZones) {
            workZones1.add(new WorkZone(workZone.provincia(), workZone.canton()));
        }
        return workZones1;
    }

    private static ArrayList<Reference> createUserReferences(List<LoginQuery.Reference> references) {
        ArrayList<Reference> references1 = new ArrayList<>();
        for (LoginQuery.Reference reference: references) {
            Reference reference1 = new Reference(reference.referencenumber(), reference.lastjob(),
                    reference.firstname(), reference.lastname(), reference.lastname2(), reference.phonenumber());
            references1.add(reference1);
        }
        return references1;
    }

    private static ArrayList<UserProfession> createUserProfessions(List<LoginQuery.Profession> professions) {
        ArrayList<UserProfession> userProfessions = new ArrayList<>();
        for (LoginQuery.Profession profession: professions) {
            UserProfession userProfession = new UserProfession(
                    new Profession(profession.profession().professionid(), profession.profession().professionname()),
                    profession.experienceyears(),
                    profession.details()
            );
            if (profession.costperhour().equals("A negociar")) {
                userProfession.setaNegociar(true);
            } else {
                userProfession.setCostperhour(Double.parseDouble(Objects.requireNonNull(profession.costperhour())));
            }
            userProfession.setReferencePictures(createReferencePictures(Objects.requireNonNull(profession.referencePictures())));
            userProfessions.add(userProfession);
        }
        return userProfessions;
    }

    private static ArrayList<ReferencePicture> createReferencePictures(List<LoginQuery.ReferencePicture> referencePictures) {
        ArrayList<ReferencePicture> referencePictures1 = new ArrayList<>();
        for (LoginQuery.ReferencePicture referencePicture: referencePictures) {
            ReferencePicture referencePicture1 = new ReferencePicture(referencePicture.imageID(), referencePicture.imageURL());
            referencePictures1.add(referencePicture1);
        }
        return referencePictures1;
    }

    public static ArrayList<Provincia> createDirecciones(List<GetDireccionesQuery.GetDireccione> direcciones) {
        ArrayList<Provincia> provincias = new ArrayList<>();
        for (GetDireccionesQuery.GetDireccione direccion: direcciones) {
            ArrayList<Canton> cantons = new ArrayList<>();
            Canton canton = null;
            for (GetDireccionesQuery.Cantone cantonI: direccion.cantones()) {
                canton = new Canton();
                canton.setName(cantonI.canton());
                for (GetDireccionesQuery.Distrito distrito: cantonI.distritos()) {
                    canton.addDistrito(distrito.distrito());
                }
                cantons.add(canton);
            }
            provincias.add(new Provincia(direccion.provincia(), cantons));
        }
        return provincias;
    }

    public static ArrayList<Profession> createProfessions(List<GetProfessionsQuery.GetProfession> professions) {
        ArrayList<Profession> professions1 = new ArrayList<>();
        for (GetProfessionsQuery.GetProfession profession: professions) {
            professions1.add(new Profession(profession.professionid(), profession.professionname()));
        }
        return professions1;
    }
}
