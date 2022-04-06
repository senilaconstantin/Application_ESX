<template>
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

        <v-btn
          color="error"
          class="mr-4"
          @click="$router.push({ name: 'register' })"
        >
          Register
        </v-btn>
      </v-form>
    </v-col>
  </v-row>
</template>

<script>
import axios from "axios";
export default {
  name: "LoginView",
  data() {
    return {
      mail: "",
      password: "",
    };
  },
  methods: {
    async login() {
      try {
        var result = await axios.post("http://localhost:8081/users/login", {
          mail: this.mail,
          password: this.password,
        });
        window.localStorage.setItem("id", result.data.id);
        this.$router.push({ name: "home" });
      } catch (e) {
        alert("Credentiale incorecte!");
      }
    },
  },
};
</script>
