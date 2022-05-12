<template>
 <v-app>
    <v-app-bar app color="primary" dark>
      <div class="d-flex align-center">
        <v-img
          alt="Gym Logo"
          class="shrink mr-2"
          contain
          src="https://cdn.vectorstock.com/i/1000x1000/17/15/strong-gym-logo-design-simple-vector-30211715.webp"
          transition="scale-transition"
          width="50"
        />

        <!-- <v-img
          alt="ESX Name"
          class="shrink mt-1 hidden-sm-and-down"
          contain
          min-width="100"
          src="https://www.hanwhasecurity.com/wp-content/uploads/2022/02/event_main_b-esx.jpg"
          width="100"
        /> -->
      </div>
      <v-spacer></v-spacer>
      <!-- <v-spacer></v-spacer> -->
      <v-toolbar-title class="text black--text">
        <span class="font-weight-light"> LoginAdmin</span>
        <span class="font-bolt"> ESX</span>
      </v-toolbar-title>

      <v-spacer></v-spacer>
    </v-app-bar>

     <v-row>
    <v-col lg="4">
      <v-form ref="form" class="ml-6 mr-6">
        <v-text-field v-model="mail" label="Email" required></v-text-field>

        <v-text-field
          v-model="password"
          label="Password"
          type="password"
          required
        ></v-text-field>

        <v-btn color="success" class="mr-4" @click="login"> Login </v-btn>

        <!-- <v-btn
          color="error"
          class="mr-4"
          @click="$router.push({ name: 'register' })"
        >
          Register
        </v-btn> -->
      </v-form>
    </v-col>
  </v-row>
  </v-app>
</template>

<script>
import axios from "axios";
export default {
  name: "LoginAdminView",
  data() {
    return {
      mail: "",
      password: "",
    };
  },
  methods: {
    async login() {
      try {
        // alert("ceva");
        var result = await axios.post("http://localhost:8081/users/login/admin", {
          mail: this.mail,
          password: this.password,
        });
        //  alert(result.data.id);
        // alert("ceva");
        window.localStorage.setItem("idAdmin", result.data.id);
       
        this.$router.push({ name: "homeAdmin" });
      } catch (e) {
        alert("Credentiale incorecte!");
      }
    },
  },
};
</script>
