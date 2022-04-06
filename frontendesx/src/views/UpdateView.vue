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

              <h1>Last Name</h1>
              <input type="text" v-model="lnChange" />

              <h1>Email</h1>
              <input type="text" v-model="emailChange" />

              <h1>Adress</h1>
              <input type="text" v-model="adressChange" />

              <!-- <v-expansion-panels v-model="panel" :disabled="disabled" multiple>
              <v-panel-header>First Name</v-panel-header>

              <v-expansion-panels v-model="panel" :disabled="disabled" multiple>
                <v-expansion-panel>
                  <v-panel-header>First Name</v-panel-header>
                  <h1>{{ user.firstName }}</h1>
                </v-expansion-panel>

               <v-expansion-panel>
                  <v-panel-header>Last Name</v-panel-header>
                  <h1>{{ user.lastName }}</h1>
                </v-expansion-panel>

                <v-expansion-panel>
                  <v-panel-header>Email</v-panel-header>
                  <h1>{{ user.mail }}</h1>
                </v-expansion-panel>

                <v-expansion-panel>
                  <v-panel-header>Adress</v-panel-header>
                  <h1>{{ user.adress }}</h1>
                </v-expansion-panel>
              </v-expansion-panels>  -->

              <v-btn
                color="success"
                class="mr-4"
                @click="$router.push({ name: 'home' })"
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
    };
  },

  async created() {
    var result = await axios.get(
      // "http://localhost:8081/users/" + window.localStorage.getItem("id")
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


