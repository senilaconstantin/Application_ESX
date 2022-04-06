<template>
  <v-row>
    <v-col lg="4">
      <v-form ref="form" class="ml-6 mr-6">
        <v-text-field v-model="id" label="ID User" required></v-text-field>

        <v-btn color="success" class="mr-4" @click="deletee"> Delete </v-btn>

      </v-form>
    </v-col>
  </v-row>
</template>

<script>
import axios from "axios";
export default {
  name: "DeleteView",
  data() {
    return {
      id: "",
    };
  },
  methods: {
    async deletee() {
      try {
        await axios.delete("http://localhost:8081/users/"+this.id)
      } catch (e) {
        alert("Ceva gresit!");
      }
    },
  },
};
</script>
