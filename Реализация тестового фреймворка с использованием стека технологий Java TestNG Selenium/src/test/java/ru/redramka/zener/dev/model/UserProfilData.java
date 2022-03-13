package ru.redramka.zener.dev.model;

public class UserProfilData {
    private String userName;
    private String userEmail;
    private String userPhone;
    private String userCity;
    private String userCompany;
    private String userINN;
    private String userMailIndex;
    private String userStreet;
    private String userHouseNumber;
    private String userRoomNumber;


    public UserProfilData with_name(String userName) {
        this.userName = userName;
        return this;
    }

    public UserProfilData with_email(String userEmail) {
        this.userEmail = userEmail;
        return this;
    }

    public UserProfilData with_mail_index(String userMailIndex){
        this.userMailIndex = userMailIndex;
        return this;
    }

    public UserProfilData with_phone(String userPhone) {
        this.userPhone = userPhone;
        return this;
    }

    public UserProfilData with_city(String userCity) {
        this.userCity = userCity;
        return this;
    }

    public UserProfilData with_steet(String userStreet){
        this.userStreet = userStreet;
        return this;
    }

    public UserProfilData with_house_number(String userHouseNumber){
        this.userHouseNumber = userHouseNumber;
        return this;
    }

    public UserProfilData with_room_number(String userRoomNumber){
        this.userRoomNumber = userRoomNumber;
        return this;
    }

    public UserProfilData with_company(String userCompany) {
        this.userCompany = userCompany;
        return this;
    }

    public UserProfilData with_inn(String userINN) {
        this.userINN = userINN;
        return this;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public String getUserCity() {
        return userCity;
    }

    public String getUserCompany() { return userCompany;}

    public String getUserINN() { return userINN; }

    public String getUserStreet(){return  userStreet;}

    public String getUserHouseNumber() { return userHouseNumber; }

    public String getUserRoomNumber() { return userRoomNumber; }

    public String getUserMailIndex() { return userMailIndex;}

    @Override
    public String toString() {
        return "UserProfilData{" +
                "userName='" + userName + '\'' +
                ", userEmail='" + userEmail + '\'' +
                ", userPhone='" + userPhone + '\'' +
                ", userCity='" + userCity + '\'' +
                ", userCompany='" + userCompany + '\'' +
                ", userINN='" + userINN + '\'' +
                ", userMailIndex='" + userMailIndex + '\'' +
                ", userStreet='" + userStreet + '\'' +
                ", userHouseNumber='" + userHouseNumber + '\'' +
                ", userRoomNumber='" + userRoomNumber + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserProfilData that = (UserProfilData) o;

        if (userName != null ? !userName.equals(that.userName) : that.userName != null) return false;
        if (userEmail != null ? !userEmail.equals(that.userEmail) : that.userEmail != null) return false;
        if (userPhone != null ? !userPhone.equals(that.userPhone) : that.userPhone != null) return false;
        if (userCity != null ? !userCity.equals(that.userCity) : that.userCity != null) return false;
        if (userCompany != null ? !userCompany.equals(that.userCompany) : that.userCompany != null) return false;
        if (userINN != null ? !userINN.equals(that.userINN) : that.userINN != null) return false;
        if (userMailIndex != null ? !userMailIndex.equals(that.userMailIndex) : that.userMailIndex != null)
            return false;
        if (userStreet != null ? !userStreet.equals(that.userStreet) : that.userStreet != null) return false;
        if (userHouseNumber != null ? !userHouseNumber.equals(that.userHouseNumber) : that.userHouseNumber != null)
            return false;
        return userRoomNumber != null ? userRoomNumber.equals(that.userRoomNumber) : that.userRoomNumber == null;
    }

    @Override
    public int hashCode() {
        int result = userName != null ? userName.hashCode() : 0;
        result = 31 * result + (userEmail != null ? userEmail.hashCode() : 0);
        result = 31 * result + (userPhone != null ? userPhone.hashCode() : 0);
        result = 31 * result + (userCity != null ? userCity.hashCode() : 0);
        result = 31 * result + (userCompany != null ? userCompany.hashCode() : 0);
        result = 31 * result + (userINN != null ? userINN.hashCode() : 0);
        result = 31 * result + (userMailIndex != null ? userMailIndex.hashCode() : 0);
        result = 31 * result + (userStreet != null ? userStreet.hashCode() : 0);
        result = 31 * result + (userHouseNumber != null ? userHouseNumber.hashCode() : 0);
        result = 31 * result + (userRoomNumber != null ? userRoomNumber.hashCode() : 0);
        return result;
    }
}
