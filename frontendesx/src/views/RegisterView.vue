<template>
  <v-form ref="form" class="ml-6 mr-6">
    <v-text-field
      v-model="firstName"
      label="First Name"
      required
    ></v-text-field>

    <v-text-field v-model="lastName" label="Last Name" required></v-text-field>

    <v-text-field v-model="mail" label="Email" required></v-text-field>

    <v-text-field
      v-model="password"
      label="Password"
      type="password"
      required
    ></v-text-field>

    <v-text-field v-model="adress" label="Adress" required></v-text-field>

    <v-btn color="success" class="mr-4" @click="register"> Register </v-btn>
  </v-form>
</template>

<script>
import axios from "axios";
export default {
  name: "RegisterView",
  data() {
    return {
      firstName: "",
      lastName: "",
      mail: "",
      password: "",
      adress: "",
    };
  },
  methods: {
    async register() {
      try {
        await axios.post("http://localhost:8081/users", {
          firstName: this.firstName,
          lastName: this.lastName,
          mail: this.mail,
          password: this.password,
          adress: this.adress,
        });
        this.$router.push({ name: "login" });
      } catch (e) {
        alert("Email deja existent!");
      }
    },
  },
};
</script>
