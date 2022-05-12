
<template>
  <v-app dark>
    <!-- <v-app-bar
      app
      color="primary"
      dark
    >
      <div class="d-flex align-center">
        <v-img
          alt="Gym Logo"
          class="shrink mr-2"
          contain
          src="https://cdn.vectorstock.com/i/1000x1000/17/15/strong-gym-logo-design-simple-vector-30211715.webp"
          transition="scale-transition"
          width="50"
        />

        <v-img
          alt="ESX Name"
          class="shrink mt-1 hidden-sm-and-down"
          contain
          min-width="100"
          src="https://www.hanwhasecurity.com/wp-content/uploads/2022/02/event_main_b-esx.jpg"
          width="100"
        />
      </div>

      <v-spacer></v-spacer>
    </v-app-bar> -->

    <v-main>
      <router-view />
    </v-main>
  </v-app>
</template>

<script>
export default {
  name: "App",

  data: () => ({
    //
  }),
};
</script>

<style>
#app {
  background: url("https://ohlaladani.com.br/wp-content/uploads/wallpaper-OHLALADANI_DESKTOP_WALLPAPERS_AVENTURA-2.jpg")
    no-repeat center center fixed !important;
  background-size: cover;
}
</style>








