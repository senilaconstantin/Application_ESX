<template>
  <v-row>
    <v-col lg="8">
      <v-form ref="form" class="ml-6 mr-6">
        <div id="app">
          <v-app id="inspire">
            <div>
              <!-- <h1>First Name</h1> -->
              <v-text-field label="First Name" type="text" v-model="fnChange" />
              <!-- {{fnChange}} -->

              <!-- <h1>Last Name</h1> -->
              <!-- <input type="text" v-model="lnChange" /> -->
              <v-text-field label="Last Name" type="text" v-model="lnChange" />

              <!-- <h1>Email</h1>
              <input type="text" v-model="emailChange" /> -->
              <v-text-field label="Email" type="text" v-model="emailChange" />

              <!-- <h1>Adress</h1>
              <input type="text" v-model="adressChange" /> -->
              
              <v-text-field label="Adress" type="text" v-model="adressChange" />

              <v-btn
                color="success"
                class="mr-4"
                @click="$router.push({ name: back1 })"
              >
                Back
              </v-btn>
              <v-btn color="success" class="mr-4" @click="update">
                Update
              </v-btn>
            </div>
          </v-app>
        </div>
      </v-form>
    </v-col>
  </v-row>
</template>

<script>
import axios from "axios";

export default {
  name: "UpdateView",
  data() {
    return {
      user: "",
      fnChange: "",
      lnChange: "",
      emailChange: "",
      adressChange: "",
      back1: window.localStorage.getItem("back"),
    };
  },

  async created() {
  // alert(this.$route.params.id);
    var result = await axios.get(
      "http://localhost:8081/users/"+this.$route.params.id
    );
    this.user = result.data;
    this.fnChange = this.user.firstName;
    this.lnChange = this.user.lastName;
    this.emailChange = this.user.mail;
    this.adressChange = this.user.adress;
  },

  methods: {
    async update() {
      try {
        await axios.put("http://localhost:8081/users", {
          id: this.user.id,
          firstName: this.fnChange,
          lastName: this.lnChange,
          mail: this.emailChange,
          password: this.user.password,
          adress: this.adressChange,
        });
        // alert(result);
        // alert(this.fnChange + " " + this.user.id);
        this.$router.push({ name: "home" });
      } catch (e) {
        alert("Email deja existent!");
      }
    },
  },
};
</script>

<style lang="scss" scoped>
input {
  border: 2px solid black;
}
</style>


